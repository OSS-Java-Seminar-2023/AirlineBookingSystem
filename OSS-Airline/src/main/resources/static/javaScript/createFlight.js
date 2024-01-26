var today = new Date();
today.setDate(today.getDate() + 1);

var formattedDate = today.toISOString().slice(0, 10);
document.getElementById('date').min = formattedDate;

function updateToOptions() {
    var fromAirportId = document.getElementById('from').value;

    var toDropdown = document.getElementById('to');

    toDropdown.innerHTML = "";

    var defaultOption = document.createElement("option");
    defaultOption.text = "-- Select To Airport --";
    defaultOption.value = "";
    toDropdown.add(defaultOption);

    var airports = /*[[${airports}]]*/ [];

    airports.forEach(function (airport) {
        if (airport.id !== fromAirportId) {
            var option = document.createElement("option");
            option.text = airport.name;
            option.value = airport.id;
            toDropdown.add(option);
        }
    });
}