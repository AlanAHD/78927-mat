from ubuntu 
run apt update
run apt install -y openjdk-19-jdk
run apt install -y mysql-server
workdir /app
add script.sql /app/script.sql
add practica04.jar /app/practica04.jar
add script.sh /app/script.sh
run chmod 755 /app/script.sh
run /etc/init.d/mysql start; mysql < /app/script.sql
cmd ["/app/script.sh"]
expose 8080
