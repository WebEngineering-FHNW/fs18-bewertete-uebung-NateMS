package gradr

class BootStrap {

    def init = { servletContext ->
        def user = new User(name: 'Pickup',  make: nissan, model: titan, year: 2012).save()
        new Vehicle(name: 'Economy', make: nissan, model: leaf, year: 2014).save()
        new Vehicle(name: 'Minivan', make: ford, model: windstar, year: 1990).save()
    }
    def destroy = {
    }
}
