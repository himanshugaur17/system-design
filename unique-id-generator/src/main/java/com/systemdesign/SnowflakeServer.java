package com.systemdesign;

import java.time.Instant;

import lombok.extern.slf4j.Slf4j;

/*
 * SnowFlakeServer to mimic servers in twitter snowflake architecture
 * It will create a 64 bit id
 */
@Slf4j
public class SnowflakeServer {
    private static final int EPOCH_BITS_COUNT = 41;
    private static final int DATA_CENTER_BITS_COUNT = 5; /* 32 data center */
    private static final int SNOWFLAKE_NODES_BITS_COUNT = 5; /* 32 machines per data center */
    private static final int SEQUENCE_BITS_COUNT = 12;
    private static final long DEFAULT_EPOCH = 1420070400000L; /*
                                                               * (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
                                                               */
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS_COUNT) - 1;
    private long epochBase;
    private int dataCenterId;
    private int machineId;
    private int sequence = 1;
    private long lastTimeStamp = -1l;

    public SnowflakeServer(int dataCenterId, int machineId) {
        this(dataCenterId, machineId, DEFAULT_EPOCH);
    }

    public SnowflakeServer(int dataCenterId, int machineId, long customEpoch) {
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
        this.epochBase = customEpoch;
    }

    public synchronized long nextId() {
        /*
         * Edge cases:
         * 1) What if (which will never happen) we exhaust all 2^41 combinations of
         * epoch?
         * We can
         * 
         * 2) What if we get too many requests and exhaust all sequence 2^12 combns
         * Wait for 1 millisecond in current, since method is synchronized, all threads
         * have to wait.
         */
        long currentTimeStamp = getReferencedTimeStamp();
        if (currentTimeStamp == lastTimeStamp && sequence == MAX_SEQUENCE) {
            log.info("we have to wait for 1ms");
            currentTimeStamp = blockingWaitForMilliSeconds(1, currentTimeStamp);
        } else {
            sequence = 0;
        }
        lastTimeStamp = currentTimeStamp;
        long id = formId(currentTimeStamp, sequence);

        log.info("snowlflake node {} present in data center {} generated unique id: {}", machineId, dataCenterId, id);
        return id;
    }

    private long formId(long currentTimeStamp, int sequence) {
        return currentTimeStamp << (DATA_CENTER_BITS_COUNT + SNOWFLAKE_NODES_BITS_COUNT + SEQUENCE_BITS_COUNT)
                | (dataCenterId << SNOWFLAKE_NODES_BITS_COUNT + SEQUENCE_BITS_COUNT)
                | (machineId << SEQUENCE_BITS_COUNT)
                | sequence;
    }

    private long getReferencedTimeStamp() {
        return Instant.now().toEpochMilli() - epochBase;
    }

    private long blockingWaitForMilliSeconds(int i, long currentTimeStamp) {
        while (getReferencedTimeStamp() - currentTimeStamp != i)
            ;
        return getReferencedTimeStamp();
    }
}
