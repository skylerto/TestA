/**
 * 
 */
function validate() {

	var ok = true;
	var p = document.getElementById("principle").value;
	if (isNaN(p) || p <= 0) {
		alert("Principle invalid!");
		ok = false;
	}
	if (!ok)
		return false;
	p = document.getElementById("interest").value;
	var check = document.getElementById("interest");
	if (isNaN(p) || p <= 0 || p >= 100 || check.checked == false) {
		if (check.checked == false) {
			alert("Please select and interest rate.");
			ok = false;
		} else {
			alert("Invalid. Must be in (0,100).");
			ok = false;
		}
	}
	return ok;
}