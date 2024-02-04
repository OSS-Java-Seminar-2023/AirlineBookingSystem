function validateDOB() {
    var dobInput = document.getElementById('passengerDOB');
    var selectedDate = new Date(dobInput.value);
    var currentDate = new Date();

    if (selectedDate > currentDate) {
        alert("Date of Birth cannot be in the future.");
        dobInput.value = getCurrentDateFormatted();
    }
}

function getCurrentDateFormatted() {
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
}