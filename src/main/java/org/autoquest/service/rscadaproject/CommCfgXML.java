package org.autoquest.service.rscadaproject;

import org.autoquest.connections.units.MBUnitSlave;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class CommCfgXML {
    private ArrayList<MBUnitSlave> MBUS = new ArrayList<>();
    private final String path;

    public CommCfgXML(String path) {
        this.path = path;
    }

    public void add(MBUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        Path file = Paths.get(path + "/Instances/Default/ScadaComm/Config/ScadaCommSvcConfig.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        sb.append("<ScadaCommSvcConfig>\n" +
                "<!-- Общие параметры -->\n" +
                "<CommonParams>\n" +
                "<Param name=\"ServerUse\" value=\"true\" descr=\"Использовать SCADA-Сервер\"/>\n" +
                "<Param name=\"ServerHost\" value=\"localhost\" descr=\"Имя компьютера или IP-адрес SCADA-Сервера\"/>\n" +
                "<Param name=\"ServerPort\" value=\"10000\" descr=\"Номер TCP-порта SCADA-Сервера\"/>\n" +
                "<Param name=\"ServerUser\" value=\"ScadaComm\" descr=\"Имя пользователя для подключения к SCADA-Серверу\"/>\n" +
                "<Param name=\"ServerPwd\" value=\"12345\" descr=\"Пароль пользователя для подключения к SCADA-Серверу\"/>\n" +
                "<Param name=\"ServerTimeout\" value=\"10000\" descr=\"Таймаут ожидания ответа SCADA-Сервера, мс\"/>\n" +
                "<Param name=\"WaitForStop\" value=\"10000\" descr=\"Ожидание остановки линий связи, мс\"/>\n" +
                "<Param name=\"SendModData\" value=\"true\" descr=\"Передавать только изменившиеся теги КП\"/>\n" +
                "<Param name=\"SendAllDataPer\" value=\"60\" descr=\"Период передачи всех тегов КП, с\"/>\n" +
                "</CommonParams>\n" +
                "<!-- Линии связи -->\n" +
                "<CommLines>\n");
        for (MBUnitSlave ms : MBUS) {
            sb.append("<!-- Линия " + index + " -->\n" +
                    "<CommLine active=\"true\" bind=\"true\" number=\"" + index + "\" name=\"" + ms.getName() + "LineComm\">\n" +
                    "<CommChannel type=\"TcpClient\">\n" +
                    "<Param name=\"Behavior\" value=\"Master\"/>\n" +
                    "<Param name=\"ConnMode\" value=\"Individual\"/>\n" +
                    "<Param name=\"DevSelMode\" value=\"ByIPAddress\"/>\n" +
                    "<Param name=\"Host\" value=\"\"/>\n" +
                    "<Param name=\"InactiveTime\" value=\"60\"/>\n" +
                    "<Param name=\"ReconnectAfter\" value=\"5\"/>\n" +
                    "<Param name=\"StayConnected\" value=\"True\"/>\n" +
                    "<Param name=\"TcpPort\" value=\"" + ms.getPort() + "\"/>\n" +
                    "</CommChannel>\n" +
                    "<LineParams>\n" +
                    "<Param name=\"ReqTriesCnt\" value=\"3\" descr=\"Количество попыток перезапроса КП при ошибке\"/>\n" +
                    "<Param name=\"CycleDelay\" value=\"0\" descr=\"Задержка после цикла опроса, мс\"/>\n" +
                    "<Param name=\"CmdEnabled\" value=\"true\" descr=\"Команды ТУ разрешены\"/>\n" +
                    "<Param name=\"ReqAfterCmd\" value=\"false\" descr=\"Опрос КП после команды ТУ\"/>\n" +
                    "<Param name=\"DetailedLog\" value=\"true\" descr=\"Записывать в журнал подробную информацию\"/>\n" +
                    "</LineParams>\n" +
                    "<CustomParams>\n" +
                    "<Param name=\"TransMode\" value=\"TCP\"/>\n" +
                    "</CustomParams>\n" +
                    "<ReqSequence>\n" +
                    "<KP active=\"true\" bind=\"true\" number=\"" + index + "\" name=\"" + ms.getName() + "\" dll=\"KpModbus.dll\" address=\"" + ms.getAddress() + "\" " +
                    "callNum=\"" + ms.getInetAddress() + "\" timeout=\"1000\" delay=\"200\" time=\"00:00:00\" period=\"00:00:00\" cmdLine=\"" + ms.getName() + "ModBusList.xml\"/>\n" +
                    "</ReqSequence>\n" +
                    "</CommLine>\n");
            index++;
        }
        sb.append("</CommLines>\n" +
                "</ScadaCommSvcConfig>");


        return sb;
    }
}
