window.ReservationView = Backbone.View.extend({

	tagName : "div", 

	initialize : function() {
		this.template = _.template(tpl.get('reservation-details'));
		this.model.bind("change", this.render, this);
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		return this;
	},

	events : {
		"change input" : "change",
		"click .save" : "saveReservation",
		"click .delete" : "deleteReservation"
	},

	change : function(event) {
		var target = event.target;
		console.log('changing ' + target.id + ' from: ' + target.defaultValue
				+ ' to: ' + target.value);
	},

	saveReservation : function() {
		this.model.set({
			pricePaid : $('#pricePaid').val(),
			isRoundTrip : $('#isRoundTrip').is(":checked"),
			origin : $('#origin').val(),
			destination : $('#destination').val()
		});
		if (this.model.isNew()) {
			var self = this;
			app.reservationList.create(this.model, {
				success : function() {
					app.navigate('reservations/' + self.model.id, false);
				}
			});
		} else {
			this.model.save();
		}

		return false;
	},

	deleteReservation : function() {
		this.model.destroy({
			success : function() {
				window.history.back();
			}
		});
		return false;
	}

});