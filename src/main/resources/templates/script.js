document.getElementById('flightForm').addEventListener('submit', function(event) {
    // Prevent the form from submitting to handle validation
    event.preventDefault();

    // Get form values
    const flightNo = document.getElementById('flightNo').value.trim();
    const flightId = document.getElementById('flightId').value.trim();
    const departureAirport = document.getElementById('departureAirport').value.trim();
    const arrivalAirport = document.getElementById('arrivalAirport').value.trim();
    const scheduledDeparture = document.getElementById('scheduledDeparture').value;
    const scheduledArrival = document.getElementById('scheduledArrival').value;
    const aircraftCode = document.getElementById('aircraftCode').value.trim();
    const status = document.getElementById('status').value;

    // 1. Check if all required fields are filled in
    if (!flightNo || !flightId || !departureAirport || !arrivalAirport || !scheduledDeparture || !scheduledArrival || !aircraftCode || !status) {
        alert('Please fill in all required fields.');
        return;
    }

    // 2. Flight Number Validation (should be a string with letters only)

    const flightNoPattern = /^[A-Za-z0-9]+$/;

    if (!flightNoPattern.test(flightNo)) {
        alert('Flight Number must be a valid string (letters and digits only, no spaces).');
        return;
    }

    // 3. Flight ID Validation (should be a number)
    if (!/^\d+$/.test(flightId)) {
        alert('Flight ID must be a valid positive integer.');
        return;
    }

    // 4. Aircraft Code Validation (should be a number)
    if (!/^\d+$/.test(aircraftCode)) {
        alert('Aircraft Code must be a valid positive integer.');
        return;
    }

    // 5. Scheduled Departure and Arrival Validation
    if (new Date(scheduledArrival) <= new Date(scheduledDeparture)) {
        alert('Scheduled arrival must be after scheduled departure.');
        return;
    }

    // 6. Status Validation (check for valid option)
    if (!['Scheduled', 'On Time', 'Delayed', 'Cancelled'].includes(status)) {
        alert('Please select a valid status.');
        return;
    }

    // If all validations pass, submit the form
    this.submit(); // Submitting the form after all checks are passed
});
