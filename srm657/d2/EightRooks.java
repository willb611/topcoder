public class EightRooks
{
	public String isCorrect(String[] board) {
		boolean[] rows = new boolean[8];
		boolean[] cols = new boolean[8];
		int rc = 0;
		for (int i = 0; i < board.length;i++) {
			for (int col =0;col<8;col++) {
				if (board[i].charAt(col) == 'R') {
					if (cols[col]) return "Incorrect";
					else if (rows[i]) return "Incorrect";
					else {
						cols[col] = true;
						rows[i] = true;
						rc++;
					}
				}
			}
		}
		if (rc != 8) return "Incorrect";
		return "Correct";
	} // meth
}