# Comandos para visualizar uso de memória da JVM

## NTM -> Native Memory Tracking

java -XX:NativeMemoryTracking=summary Teste

- jcmd

- jcmd <pid>

- jcmd {PID} VM.native_memory summary

- jcmd {PID} GC.heap_info