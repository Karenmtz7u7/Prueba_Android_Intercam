package com.aplication.karen.mtz.prueba.data.Model

data class BeerModel(
    var id: Int,
    var name: String,
    var tagline: String,
    var first_brewed: String,
    var description: String,
    var image_url: String,
    var abv : Double,
    var ibu : Double,
    var target_fg : Int,
    var target_og : Double,
    var ebc : Int,
    var srm : Double,
    var ph : Double,
    var attenuation_level : Double,
    var volume: Volume,
    var boil_volume: BoilVolume,
    var method: Method,
    var ingredients: Ingredients,
    var food_pairing: ArrayList<String>,
    var brewers_tips: String,
    var contributed_by: String,
    var isfavorite: Boolean = false
)

class Amount {
    var value = 0.0
    lateinit var unit: String
}

class BoilVolume {
    var value = 0
    lateinit var unit: String
}

class Fermentation {
    lateinit var temp: Temp
}

class Hop {
    lateinit var name: String
    lateinit var amount: Amount
    lateinit var add: String
    lateinit var attribute: String
}

class Ingredients {
    lateinit var malt: ArrayList<Malt>
    lateinit var hops: ArrayList<Hop>
    lateinit var yeast: String
}

class Malt {
    lateinit var name: String
    lateinit var amount: Amount
}

class MashTemp {
    lateinit var  temp: Temp
    var duration = 0
}

class Method {
    lateinit var mash_temp: ArrayList<MashTemp>
    lateinit var fermentation: Fermentation
    lateinit var twist: Any
}

class Temp {
    var value = 0
    lateinit var unit: String
}

class Volume {
    var value = 0
    lateinit var unit: String
}


