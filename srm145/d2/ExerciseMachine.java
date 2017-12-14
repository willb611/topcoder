public class ExerciseMachine {
	public int getPercentages(String time) {
		int seconds = getSecs(time);
		int count = 0;
		for (int i = 1; i < 100; i++) {
		 	if ((i*seconds) % 100 == 0) {
		 		count++;
		 	}
		}
		return count;
	}
	private int getSecs(String t) {
		int hours = Integer.valueOf(t.substring(0, 2));
		int minutes = Integer.valueOf(t.substring(3, 5));
		int seconds = Integer.valueOf(t.substring(6, 8));
		int mins = (hours * 60) + minutes;
		return (mins * 60) + seconds;
	}
}