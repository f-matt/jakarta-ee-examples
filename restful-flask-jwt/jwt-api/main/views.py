from flask import jsonify
from flask_restful import Resource
from flask_jwt_extended import jwt_required, create_access_token

class IndexEndpoint(Resource):
    def get(self):
        return jsonify("online")

class JwtEndpoint(Resource):
    @jwt_required()
    def get(self):
        return {"message": "hello world"}

class LoginEndpoint(Resource):
    def get(self):
        access_token = create_access_token(identity='user')
        return jsonify(message=access_token)
