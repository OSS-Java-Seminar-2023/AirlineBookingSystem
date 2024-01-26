function checkIATALength() {
    var iataInput = document.getElementById('IATA');
    var iataLengthMessage = document.getElementById('iataLengthMessage');

    var cleanedInput = iataInput.value.replace(/[^A-Z]/g, '').toUpperCase();

    if (cleanedInput.length > 3) {
        iataLengthMessage.style.color = 'red';
        iataLengthMessage.innerText = 'IATA Code must be 3 uppercase letters or less.';
    } else {
        iataInput.value = cleanedInput;
        iataLengthMessage.innerText = '';
    }
}