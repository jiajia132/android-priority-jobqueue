package com.path.android.jobqueue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * Base class for all of your jobs.
 * If you were using {@link BaseJob}, please move to this instance since BaseJob will be removed from the public api.
 */
@SuppressWarnings("deprecation")
abstract public class Job extends BaseJob implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient int priority;
    private transient long delayInMs;
    private final Set<String> readonlyTags;

    protected Job(Params params) {
        super(params.doesRequireNetwork(), params.isPersistent(), params.getGroupId());
        this.priority = params.getPriority();
        this.delayInMs = params.getDelayMs();
        Set<String> tags = params.getTags();
        this.readonlyTags = tags == null ? null : Collections.unmodifiableSet(tags);
    }

    /**
     * used by {@link JobManager} to assign proper priority at the time job is added.
     * This field is not preserved!
     * @return priority (higher = better)
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * used by {@link JobManager} to assign proper delay at the time job is added.
     * This field is not preserved!
     * @return delay in ms
     */
    public final long getDelayInMs() {
        return delayInMs;
    }

    /**
     * Returns a readonly set of tags attached to this Job.
     * @return Set of Tags. If tags do not exists, returns null.
     */
    public final Set<String> getTags() {
        return readonlyTags;
    }
}
