package ru.spb.nicetu.oestik.prop;

import org.ini4j.Ini;
import org.nic.oestik.core.props.MainAndTheodoliteParametersInitializer;
import org.nic.oestik.core.props.MainOestikParam;

/**
 * Класс загрузки и инициализации главных параметров.
 * 
 * @author Иван Савичев
 * @author Валерий Голубев
 */
public class OestikFxParametersInitializer extends MainAndTheodoliteParametersInitializer{

    /**
     * Инициализация параметров.
     * 
     * @param ini - стартовые параметры
     */
    @Override
    public void initParam(final Ini ini) {
        super.initParam(ini);

        // может отсутствовать в конфигурации
        OestikFxProperties.setConnectTimeout(((Integer) getParameterValue(ini, MAIN_SECTION, INT,
                MainOestikParam.CONNECT_TIMEOUT.getName(), null, false, OestikFxProperties.getConnectTimeout()))
                .intValue());

        // может отсутствовать в конфигурации
        OestikFxProperties.setRequestTimeout(((Integer) getParameterValue(ini, MAIN_SECTION, INT,
                MainOestikParam.REQUEST_TIMEOUT.getName(), null, false, OestikFxProperties.getRequestTimeout()))
                .intValue());

        // может отсутствовать в конфигурации
        OestikFxProperties.setStatusRefreshTime(((Integer) getParameterValue(ini, MAIN_SECTION, INT,
                MainOestikParam.STATUS_REFRESH_TIME.getName(), null, false, OestikFxProperties.getStatusRefreshTime()))
                .intValue());
    }

}
