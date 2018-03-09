package sched;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class EdfScheduler extends Scheduler {

    /**
     * Schedules the task set in the interval [0, scheduleEndTime) using
     * the EDF scheduling algorithm.
     * @param taskSet - the task set, defining job parameters
     * @param scheduleEndTime - the end of the scheduling interval (exclusive)
     * @return a schedule of which jobs to run for which time intervals
     */
    @Override
    public ArrayList<ScheduleInterval> scheduleTaskSet(ArrayList<Task> taskSet, int scheduleEndTime) {
        ArrayList<ScheduleInterval> schedule = new ArrayList<ScheduleInterval>();

        // Pre-calculate all jobs released in the interval
        HashMap<Integer, ArrayList<Job>> jobReleases = this.calculateJobReleases(taskSet, scheduleEndTime);

        // TODO: implement me!

        PriorityQueue<Job> queue = new PriorityQueue<Job>();
        
        //add all jobs released at 0 to the queue
        for (Job j: jobReleases.get(0)) {
        	queue.add(j);
        }
        
        // lastJob keeps track of the previous run job for jobs that stay more than 1 time slice
        Job lastJob = queue.remove();
        
        // pretty much one iteration of the loop later
        lastJob.execute(1);
        if (lastJob.getRemainingTime() != 0)
        	queue.add(lastJob);
        else
        	schedule.add(new ScheduleInterval(lastJob, 0, 1));
        
        // i keeps track of the time slice, startedTime is when the lastJob started
        int i = 1;
        int startedTime = 0;
        
        // loop for all remaining time slices
        while(i<scheduleEndTime) {
        	
        	// add all the jobs that are released at this time into the queue
        	if (jobReleases.get(i) != null) {
	        	for (Job j: jobReleases.get(i)) {
	        		queue.add(j);
	        	}
        	}
        	
        	//if the queue is empty do nothing
        	if (!queue.isEmpty()) {
        		
	        	Job newJob = queue.remove();
	        	
	        	//if lastJob has either finished or is preempted
	        	if (newJob != lastJob) {
	        		
	        		// if lastJob is preempted, add the time it executed to the schedule
	        		if (lastJob.getRemainingTime() != 0)
	        			schedule.add(new ScheduleInterval(lastJob, startedTime, i));
	        		
	        		lastJob = newJob;
	        		startedTime = i;
	        	}
	        	newJob.execute(1);
	        	
	        	// if newJob isn't done yet, add it back into queue, otherwise add to schedule
	        	if (newJob.getRemainingTime() != 0)
	        			queue.add(newJob);
	        	else {
	        		schedule.add(new ScheduleInterval(newJob, startedTime, i+1));
	        	}
        	}
        	else
        		startedTime = i;
	        i++;
        }
        
        // if the last job was started but didnt finish within the endtime, add time it executed to schedule
        if (lastJob.getRemainingTime() != 0)
        	schedule.add(new ScheduleInterval(lastJob, startedTime, i));
        
        return schedule;
    }
}
