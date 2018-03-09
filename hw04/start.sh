#!/usr/bin/env bash

MEMORY="-Xms64m -Xmx64m "

#SERIAL_GC

GC="-XX:+UseSerialGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_serial -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hw04.jar > log/gc_serial_app.log

#Parallel_GC

GC="-XX:+UseParallelGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_parallel -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hw04.jar > log/gc_parallel_app.log

#CMS_GC

GC="-XX:+UseConcMarkSweepGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_cms -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hw04.jar > log/gc_cms_app.log

#G1_GC

GC="-XX:+UseG1GC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_g1 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hw04.jar > log/gc_g1_app.log