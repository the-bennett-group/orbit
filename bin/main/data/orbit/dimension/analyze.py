import json 
import sys
import itertools
from typing import Iterable

biomes = json.load(open(f"{sys.argv[1]}.json"))["generator"]["biome_source"]["biomes"]


PARAMS = ["temperature", "humidity", "continentalness", "erosion", "weirdness", "offset", "depth"] 
def iterprint(iter: Iterable): 
    for val in iter: 
        print(val)

def analyze_biomes(biomes: dict): 
    decoded = []

    for biome in biomes:
        parameters = biome["parameters"]
        param_list = ["temperature", "humidity", "continentalness", "erosion", "weirdness", "offset", "depth"]
        decoded_biome = {} # one analyzed dict will look like [Name, T, H, C, E, W, O, D]

        for param in param_list: 
            decoded_biome["name"] = biome["biome"].split("orbit:")[1] # strip "orbit:" namespacing
            if type(parameters[param]) is list: 
                decoded_biome[param] = tuple(parameters[param])
            else: 
                decoded_biome[param] = (parameters[param], parameters[param]) # ensure it's a list for analysis purposes
                
        decoded.append(decoded_biome)

    return decoded 

decoded = analyze_biomes(biomes)

# now for the fun!

def get(param, decoded): 
    return [tup for tup in zip([biome["name"] for biome in decoded], [biome[param] for biome in decoded])]

args = sys.argv[2:]
for i, arg in enumerate(args): 
    match arg.lower(): 
        case "-p"|"-parameter": # get a parameter for analysis
            param = args[i+1]
            isolated_param = get(param, decoded)
            isolated_param.sort(key=lambda tup: tup[1][0]) # sort in increasing order fo first param
            print(f"{' '*7}{param.upper()}")

            iterprint(isolated_param)
        
        case "-b"|"-biome": # get a biome for analysis
            param = args[i+1].lower()
            

            for param in biome.keys(): 
                print(f"{param}: {biome[param]}")

        case "-o"|"-overlap": # find overlaps between two biomes
            param = args[i+1].lower()
            biome_names = param.split(",")
            
            isolated_params = []
            for param in PARAMS:
                isolated_param = []
                for name in biome_names: 
                    biome = next((biome for biome in decoded if biome["name"] == name), None)
                    isolated_param.append((biome['name'], biome[param]))
                
                isolated_param.sort(key= lambda tup: tup[1][0]) # sort by first entry in parameter value list
                isolated_params.append((param, *isolated_param))
            
            iterprint(isolated_params)
                
                



