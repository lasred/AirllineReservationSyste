//should be easily extendable
window.EntityListView = Backbone.View.extend({

    tagName:'ul',

    initialize:function () {
        this.model.bind("reset", this.render, this);
        var self = this;
        var type = this.options.type;
        this.model.bind("add", function (res) {
          $(self.el).append(new EntityListItemView({model:res, type: type}).render().el);        		
        });
    },

    render:function (eventName) {
    	var type = this.options.type;
        _.each(this.model.models, function (res) {
            $(this.el).append(new EntityListItemView({model:res, type: type}).render().el);
        }, this);
        return this;
    }
});

window.EntityListItemView = Backbone.View.extend({

    tagName:"li",

    initialize:function () {
    	if(this.options.type == "reservation") {
            this.template = _.template(tpl.get('reservation-list-item'));
    	} else {
            this.template = _.template(tpl.get('flight-list-item'));    		
    	}
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render:function (eventName) {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});