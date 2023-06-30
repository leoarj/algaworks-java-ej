# Comandos para visualizar uso de memória da JVM

## NTM -> Native Memory Tracking

java -XX:NativeMemoryTracking=summary Teste

- jcmd

- jcmd <pid>

- jcmd 2275 VM.native_memory summary

- jcmd 2275 GC.heap_info