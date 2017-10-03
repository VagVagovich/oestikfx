package ru.spb.nicetu.oestik.prop;

import org.nic.oestik.core.props.CurrentPropertiesInitializer;
import org.nic.oestik.core.props.OestikProperties;

/**
 * Класс основных пармаметров комплекса(комплексов).
 * 
 * @author N/A
 * @author Валерий Голубев
 * @author Иван Савичев
 */
public class OestikFxProperties extends OestikProperties {

    /** Время ожидания обновления статуса в миллисекундах. */
    private static int statusRefreshTime = 300;

    /**
     * Пустой конструктор по умолчанию
     */
    protected OestikFxProperties() {
        // nothing
    }

    /**
     * загрузить свойства
     * 
     * @param iniFileInUserDir - путь к файлу начальных настроек в директории пользователя
     * @param iniFileInInstallDir - путь к файлу начальных настроек в директории установки
     */
    public static void loadProperties(final String iniFileInUserDir,
            final String iniFileInInstallDir) {
        OestikFxParametersInitializer initializer = new OestikFxParametersInitializer();
        initializer.loadFromFile(iniFileInUserDir, iniFileInInstallDir);
    }

    /**
     * Получить время задержки между запросами получения статуса в миллисекундах.
     * 
     * @return время задержки между запросами получения статуса в миллисекундах
     */
    public static int getStatusRefreshTime() {
        return statusRefreshTime;
    }

    

    /**
     * @param statusRefreshTime the statusRefreshTime to set
     */
    public static void setStatusRefreshTime(int statusRefreshTime) {
        OestikFxProperties.statusRefreshTime = statusRefreshTime;
    }

    

}
