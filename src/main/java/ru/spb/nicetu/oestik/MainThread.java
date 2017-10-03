package ru.spb.nicetu.oestik;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.nic.oestik.core.props.OestikProperties;
import org.nic.oestik.core.serverconnecter.ServerConnecterFactory;

import ru.spb.nicetu.oestik.prop.OestikFxProperties;
import ru.spb.nicetu.oestik.threads.StatusThread;
import ru.spb.nicetu.oestik.utils.FileSystemUtils;
import ru.spb.nicetu.oestik.utils.SubDirectory;

public class MainThread {
    
    /** Путь к файлу конфигурациии ПК управления в пользовательской директории */
    private static final String CONF_IN_USER_DIR = "/oestikfx/oestik.ini";
    /** Логгер */
    private static final Logger LOG = Logger.getLogger(MainThread.class);
    /** Поток работы со статусами */
    private static StatusThread statusThread;
    
    public static void main(String[] args) {
        OestikFxProperties.loadProperties(CONF_IN_USER_DIR,
                ""/*FileSystemPaths.INI_FILE.getPath(PK_DIR, StatusWindow.class)*/);
        configurateLogger();
        initServerConnecter();
        
        createAndStartStatusThread();
        
        StatusWindow.startFx(args);
    }
    
    /**
     * Сконфигурировать логгер
     */
    private static void configurateLogger() {
        File log4jConfig;
        try {
            log4jConfig = FileSystemUtils.getExistingFile("/oestikfx/log4j.xml",
                    SubDirectory.TMP_DIR);
            DOMConfigurator.configure(log4jConfig.getAbsolutePath());
        } catch (FileNotFoundException e) {
            
        }
    }
    
    /**
     * Проинициализировать соединение с сервером
     */
    private static void initServerConnecter() {
        if (!OestikProperties.isEmulationMode()) {
            ServerConnecterFactory.initServerConnecter();
        } else {
            ServerConnecterFactory.initEmulationServerConnecter();
        }
    }
    
    /**
     * Создает и запускает поток статуса
     */
    private static void createAndStartStatusThread() {
        statusThread = new StatusThread(OestikFxProperties.getStatusRefreshTime());
        statusThread.start();
    }
    
    public static void stop() {
        statusThread.interrupt();
    }
    
}
