package ru.spb.nicetu.oestik.threads;

import javax.swing.SwingUtilities;

import org.nic.oestik.core.model.status.Status;
import org.nic.oestik.core.model.status.StatusStorage;
import org.nic.oestik.core.mpi.MPI;
import org.nic.oestik.core.serverconnecter.ServerConnecterFactory;

/**
 * Поток работающий со статусами
 */
public class StatusThread extends Thread {

    /** Предыдущий статус */
    private final StatusStorage statusHolder;
    /** Задержка между запросами на получение статуса в миллисекундах */
    private final int delay;

    /**
     * Создать поток для работы со статусами
     * 
     * @param delay - задержка между запросами на получение статуса в миллисекундах
     */
    public StatusThread(int delay) {
        setName("StatusThread");
        this.delay = delay;
        statusHolder = new StatusStorage(16);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            final Status newStatus = ServerConnecterFactory.getServerConnecter().getVriStatus();

            try {
//                for (int i = 0; i < OestikProperties.getNumTheodolite(); i++) {
//                    if (statusHolder.getLastStatus() == null
//                            || (statusHolder.getLastStatus().isConnectionError(i)
//                                    && !newStatus.isConnectionError(i))) {
//                        MainAndTheodoliteParametersInitializer
//                                .setDeadZoneParameters(new DeadZone(newStatus.getDeadZone(i)), i);
//                    }
//                }
                
//                final List<StatusAlert> statusAlerts = AlertMessagesUtil.getStatusAlerts(
//                        OestikProperties.getNumTheodolite(), newStatus,
//                        statusHolder.getLastStatus());

                statusHolder.addStatus(newStatus);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
//                        MPI.newStatusAlerts(statusAlerts);
                        MPI.newStatus(newStatus);
                    }
                });
            } catch (NullPointerException ex) {
                // Данная конструкция с try - catch сделана для восстановления связи с РИВ-сервером
                statusHolder.addStatus(newStatus);
            }

            try {
                sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Установить настройки хранилища статусов с новым параметром частоты
     * 
     * @param frequency - частота
     */
    public void resetStatusHolderWithOtherFrequence(int frequency) {
        statusHolder.resetStatusStorageWithNewFrequency(frequency);
    }

    /**
     * Получить количество свободных кадров на видеорегистраторе по данным последнего статуса
     * 
     * @param theodoliteIdx - номер теодолита
     * @return количество свободных кадров
     */
    public long getNumFreeFrames(int theodoliteIdx) {
        return statusHolder.getLastStatus().getNumFreeFrames(theodoliteIdx);
    }

    /**
     * Проверить, идет ли быстрая очистка на теодолитах
     * 
     * @return результат проверки на быструю очистку, true, если очистка идет хотя бы на 1 канале
     *         хотя бы 1 из теодолитов
     */
    public boolean isFastFreeing() {
        return statusHolder.getLastStatus().isTheodolitsFastFreeing();
    }

}
