## 配置环境变量
## vim ~/.bashrc
### export KAFKA_HOME="/home/dev/midware/kafka_2.13-3.1.0"
### export KAFKA_MANAGER_HOME="/home/dev/midware/cmak-3.0.0.5"

nohup ${KAFKA_HOME}/bin/zookeeper-server-start.sh ${KAFKA_HOME}/config/zookeeper.properties > logs/zk.log 2>&1 &
nohup ${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/server.properties > logs/kafka.log 2>&1 &
nohup ${KAFKA_MANAGER_HOME}/bin/cmak > logs/kafka_manager.log 2>&1 &