window.HeaderView = Backbone.View.extend({

    initialize: function() {
        this.template = _.template(tpl.get('header'));
    },

    render: function(eventName) {
		$(this.el).html(this.template());
		return this;
    },

    events: {
		"click .new"    : "newReservation"
    },

	newReservation: function(event) {
		if(this.$('.new').text() == "New Reservation") {
			app.navigate("reservations/new", true);			
		} else {
			app.navigate("flights/new", true);
		}
		return false;
	}
});