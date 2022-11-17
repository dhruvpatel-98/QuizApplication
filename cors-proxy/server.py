# -*- coding: utf-8 -*-
import requests
from flask import Flask, request
from flask_cors import CORS, cross_origin
#
# pip3 install flask, requests, flask_cors
#
#

#
#
# On Windows get the the machine's IP address with ipconfig
# On *nix get the the machine's IP address with iterface config:  ifconfig
#
url = 'http://localhost:8182/'
#172.31.208.1

app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

#
#
# curl http://localhost:5000/get
#
@app.route("/")
def hello_world():
    response = requests.get(url, headers={'Content-type': "text/html; charset=UTF-8"}, stream=True)
    data = response.raw.read()
    print(data, response)
    return data

#
# curl -v -d "value=99" -X POST "http://localhost:5000/store"
#
@app.route("/store", methods=['POST','PUT'])
def store_score():
    value = request.form.get('value')
    print("My value: ", value)
    myData = { "d1": 83, "myValue" : " " + value + " " }
    print(myData)
    response = requests.post(url, headers={'Content-type': "text/html; charset=UTF-8"}, data=myData) # {"a": 1, "b": 2}
    print(response)
    return "good!"

@app.route("/save", methods=['POST', 'PUT'])
def store_bookmark():
    value = request.form.get('value')

if __name__=="__main__":
    app.run(port=5000, debug=True)

