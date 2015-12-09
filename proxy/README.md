# Proxy attack
How to run?

java SOCKS.java

java Client.java "ip" "port" "mode"

IP: localhost by default

porta: 1080 by default

mode: CONNECT_MODE or UDP_MODE


Note: UDP_MODE permite o envio de pacotes de UDP para o proxy server, mas contém algumas lacunas. Este projeto que serviu de base para esta implementação tem muitos mais extras que mais tarde podemos ver se são relevantes ou não.

Note2: As configurações são feitas no "socks.properties"

Note3:Se correrem o SocksEcho.java em detrimento do Client.java, é a versão cliente que estava implementada neste projeto mas é um cliente GUI, se quiserem tambem podem experimentar.
