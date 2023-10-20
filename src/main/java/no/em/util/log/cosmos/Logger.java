package no.em.util.log.cosmos;

import no.em.util.log.cosmos.models.PayloadLog;

/**
 * @since 2.0
 */
public interface Logger {
  int log(PayloadLog payload);
  boolean workflowExists(String workflowId);
}
