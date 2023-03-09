package com.systemdesign;

/*
 * SnowFlakeServer to mimic servers in twitter snowflake architecture
 * It will create a 64 bit id
 */
public class SnowflakeServer {
    private static final int EPOCH_BITS_COUNT = 41;
    private static final int DATA_CENTER_BITS_COUNT = 5; /* 32 data center */
    private static final int SNOWFLAKE_NODES_BITS_COUNT = 5; /* 32 machines per data center */
    private static final int SEQUENCE_BITS_COUNT = 12;
    private static final long DEFAULT_EPOCH = 1420070400000L; /*
                                                               * (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
                                                               */
    private static long epochBase;

    public SnowflakeServer(int dataCenterId, int machineId) {
        this(dataCenterId, machineId, DEFAULT_EPOCH);
    }

    public SnowflakeServer(int dataCenterId, int machineId, long customEpoch) {
        
    }
}
