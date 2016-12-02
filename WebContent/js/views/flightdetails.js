window.FlightView = Backbone.View.extend({

	tagName : "div", 

	initialize : function() {
		console.log("stupid");
		this.template = _.template(tpl.get('flight-details'));
		console.log("smart");
		this.model.bind("change", this.render, this);
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		return this;
	},

	events : {
		"change input" : "change",
		"click .save" : "saveFlight",
		"click .delete" : "deleteFlight"
	},

	change : function(event) {
		var target = event.target;
		console.log('changing ' + target.id + ' from: ' + target.defaultValue
				+ ' to: ' + target.value);
	},

	saveFlight : function() {
		this.model.set({
			departDate : $('#departDate').val(),
			basePrice : $('#basePrice').is(":checked"),
			origin : $('#origin').val(),
			destination : $('#destination').val()
		});
		if (this.model.isNew()) {
			var self = this;
			app.flightList.create(this.model, {
				success : function() {
					app.navigate('flights/' + self.model.id, false);
				}
			});
		} else {
			alert()
			this.model.save();
		}

		return false;
	},

	deleteFlight : function() {
		this.model.destroy({
			success : function() {
				window.history.back();
			}
		});
		return false;
	}
});