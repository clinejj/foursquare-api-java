import json
import os
import re
import requests
import sys
import time

def main(argv):
	# This is the token for the first test user
	oauth_token = "SSZ320G3DPH2EJZ5543RUAQ0CLW2YMTFSHQ2YCTQOBQIZL5Z" 
	version = time.strftime("%Y%m%d")
	if (len(argv) == 1):
		version = argv[0]

	endpoints = {}
	f = open("endpoints.csv", 'r')
	for line in f:
		lineSplit = re.split(r'\t+', line.strip())
		endpoints[lineSplit[0]] = './' + lineSplit[1]
	f.close()

	for endpoint, json_file in endpoints.iteritems():
		url = endpoint + "&oauth_token=" + oauth_token + "&v=" + version
		print 'Getting ' + url
		response = requests.get(url)

		if "This endpoint only supports POST." in response.text:
			print 'Posting ' + url
			response = requests.post(url)
		
		folder = json_file.split('/')[1]
		if not os.path.exists(folder):
			os.makedirs(folder)

		with open(json_file, 'w+') as out:
			json.dump(response.json(), out, sort_keys=False, indent=4, separators=(',', ': '))

if __name__ == "__main__":
   main(sys.argv[1:])