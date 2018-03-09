package sched;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class FifoScheduler extends Scheduler {

    /**
     * Schedules the task set in the interval [0, scheduleEndTime) using
     * the FIFO scheduling algorithm.
     * @param taskSet - the task set, defining job parameters
     * @param scheduleEndTime - the end of the scheduling interval (exclusive)
     * @return a schedule of which jobs to run for which time intervals
     */
    @Override
    public ArrayList<ScheduleInterval> scheduleTaskSet(ArrayList<Task> taskSet, int scheduleEndTime) {
        ArrayList<ScheduleInterval> schedule = new ArrayList<ScheduleInterval>();

        // Pre-calculate all jobs released in the interval
        HashMap<Integer, ArrayList<Job>> jobReleases = this.calculateJobReleases(taskSet, scheduleEndTime);

        ArrayList<Job> releases;
        // TODO: implement me!
        
        int start = 0;
        
        //iterate through all jobs, earliest release first, adding to schedule
        for (Integer i: jobReleases.keySet()) {
        	for (Job j: jobReleases.get(i)) {
        		if (start < j.getRelease())
        			start = j.getRelease();
        		schedule.add(new ScheduleInterval(j, start, start+j.getRemainingTime()));
        		start+=j.getRemainingTime();
        	}
        }

        return schedule;
    }
}
