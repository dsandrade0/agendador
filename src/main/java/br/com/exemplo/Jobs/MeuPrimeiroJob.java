package br.com.exemplo.Jobs;

import br.com.exemplo.Annotations.Intervalo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Executando o Job de 10 em 10 segundos
@Intervalo("0/10 * * ? * *")
public class MeuPrimeiroJob implements Job {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("Rodando ....");
    }
}
