window.Reservation = Backbone.Model.extend({
	urlRoot: "api/reservations",
	defaults: {
		id: null,
		origin: "",
		destination: "",
		pricePaid: 0.0,
		isRoundTrip: true
	 }
});

window.ReservationCollection = Backbone.Collection.extend({
	model: Reservation,
	url: "api/reservations"
});

window.Flight = Backbone.Model.extend({
	urlRoot: "api/flights",
	defaults: {
		id: null,
		origin: "",
		destination: "",
		departDate: null,
		basePrice: 0.0
	 }
});

window.FlightCollection = Backbone.Collection.extend({
	model: Flight,
	url: "api/flights"
});

window.Airline = Backbone.Model.extend({
	urlRoot: "api/airlines",
	defaults: {
		id: null,
		name: "",
	}
})

window.AirlineCollection = Backbone.Collection.extend({
	model: Airline,
	url: "api/airlines"
})