#Grid demo for web using Selenium + TestNG
Imp: TestNG.xml or tests can be run from either hub or nodes
As hub and nodes are connected through grid, so test request come to grid server
grid server then decide which test should get run on which node

#preconditions:
1. download selenium-server-standalone-2.53.1.jar  
link: http://selenium-release.storage.googleapis.com/index.html?path=2.53/

2. download chrome driver , save driver as "chromedriver" which need to pass in command 
link: http://chromedriver.storage.googleapis.com/index.html?path=2.23/

3. run all command from same directory where both above jars are present
Note: Every node should have both the jars + all browsers should be installed on every node as per requirement



#steps:
1. start hub 
java -jar selenium-server-standalone-2.53.1.jar -role hub

verify hub start :  http://localhost:4444/grid/console#
Note: localhost because current machine is hub


2. register nodes from local machines

firefox:
java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://0.0.0.0:4444/grid/register/ -browser browserName="firefox,maxInstances=5" -maxSession 5 -port 5555

chrome:
java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://0.0.0.0:4444/grid/register/  -Dwebdriver.chrome.driver=chromedriver -browser browserName="chrome,maxInstances=5" -maxSession 4 -host 0.0.0.0 -port 5556

Notes: 
nodes are in same machine hence hub is given as http://0.0.0.0:4444/grid/register/
verify hub terminal for info regarding node registration


3. register nodes on remote machine
local hub ip : 192.168.2.109
remote node ip:  192.168.2.148

firefox:
java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://192.168.2.109:4444/grid/register/ -browser browserName="firefox,maxInstances=5" -maxSession 5  -port 5558

chrome:
java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://192.168.2.109:4444/grid/register/  -Dwebdriver.chrome.driver=chromedriver -browser browserName="chrome,maxInstances=5" -maxSession 4 -host 0.0.0.0 -port 5557

Note: hub is remote machine hence hub ip is given as http://192.168.2.109:4444/grid/register/


4. Now we have four nodes, two local mac nodes for mozilla & chrome and two in remote window machine for mozilla & chrome


5. Go to testng file and run it

#note: to test on safari + ie:
6. start safari node
java -jar selenium-server-standalone-2.47.1.jar -role node -hub http://192.168.2.109:4444/grid/register/ -browser browserName="safari,maxInstances=5" -maxSession 4 -host 0.0.0.0 -port 5557

-selenium has support for safari till 2.47.1 only hence used different stand alone jar in command
-also for safari we need a safari extension to run using webdriver 
download SafariDriver.safariextz and double click file | http://elementalselenium.com/tips/69-safari

7. start ie drive
java -jar selenium-server-standalone-2.53.1.jar -role node -hub http://192.168.2.109:4444/grid/register/ -Dwebdriver.ie.driver=IEDriverServer.exe -browser browserName="ie,platform=WINDOWS" -port 5557






#to run grid using json files
--json file should be present along with jars

1. start hub in same way
java -jar selenium-server-standalone-2.53.1.jar -role hub


2. register nodes from local machines
case 1: firefox and chrome on different node
java -jar selenium-server-standalone-2.53.1.jar -role node -nodeConfig local_ff.json
java -jar selenium-server-standalone-2.53.1.jar -role node -nodeConfig local_chrome.json

case 2: both browser on same node
java -jar selenium-server-standalone-2.53.1.jar -role node -nodeConfig local_ff_ch.json

case 3: both browser on remote node
java -jar selenium-server-standalone-2.53.1.jar -role node -nodeConfig remote_ff_ch.json

note: 
remote_ff_ch.json
"host": 192.168.2.148,   | ip of node
"hubHost": 192.168.2.109 | ip of hub

local_ff_ch.json
both will be 0.0.0.0 i.e. local host



#Selenium Grid + TestNG parallel Testing
To handle test cases in different ways using testng
https://docs.google.com/document/d/1QQmjTHFYTn0xR4mc9FzkPZv1RMeHJmIU1OvhCJs-ygM



