window.FlightView = Backbone.View.extend({

	tagName : "div", 

	initialize : function() {
		this.template = _.template(tpl.get('flight-details'));
		this.model.bind("change", this.render, this);
	},

	render : function(eventName) {
		$(this.el).html(this.template(this.model.toJSON()));
		//populate 
		//debugger;
		//$.each(this.options.airlines, function(airline) {
		//	debugger;
		//    airlines.append($("<option />").val(airline.id).text(airline.name));
	//	});
		return this;
	},

	events : {
		"change input" : "change",
		"click .save" : "saveFlight",
		"click .delete" : "deleteFlight"
	},

	showAirlines : function() {
		var select = document.getElementById("airline"); 
		var airlines = this.options.airlines;			
		for(var i = 0; i < airlines.length; i++) {
		    var attributes = airlines.models[i].attributes;
		    var el = document.createElement("option");
		    el.textContent = attributes.name;
		    el.value = attributes.id;
		    select.appendChild(el);
		}
	},
	
	change : function(event) {
		var target = event.target;
		console.log('changing ' + target.id + ' from: ' + target.defaultValue
				+ ' to: ' + target.value);
	},

	saveFlight : function() {
		this.model.set({
			departDate : $('#departDate').val(),
			basePrice : $('#basePrice').val(),
			origin : $('#origin').val(),
			destination : $('#destination').val(),
			airline : { 
				id: $('#airline').val(),
				name: ""
			}
		});
		if (this.model.isNew()) {
			var self = this;
			app.flightList.create(this.model, {
				success : function() {
					app.navigate('flights/' + self.model.id, false);
				}
			});
		} else {
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