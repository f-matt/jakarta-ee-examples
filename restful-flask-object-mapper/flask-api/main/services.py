from flask import jsonify, request, abort
from flask_restful import Resource, marshal_with
from marshmallow import Schema, fields
from main.models import Item

from datetime import datetime

items = [
    Item(id=1, name='First Item', full_description='Description of the first item', registration_date=datetime(2023, 1, 1), quantity=100, active=True),
    Item(id=2, name='Second Item', full_description='Description of the second item', registration_date=datetime(2023, 3, 31), quantity=10, active=False),
    Item(id=3, name='Third Item', full_description='Description of the third item', registration_date=datetime(2023, 12, 31), quantity=25, active=True),
    Item(id=4, name='Third Item', full_description=None, registration_date=None, quantity=75, active=True),
]

class IndexEndpoint(Resource):
    def get(self):
        return jsonify("online")


class ItemSchema(Schema):
    id = fields.Int()
    name = fields.Str()
    active = fields.Boolean()


class ItemsService(Resource):
    @marshal_with(Item.resource_fields)
    def get(self):
        schema = ItemSchema()
        errors = schema.validate(request.args)
        if errors:
            abort(400, str(errors))

        params = schema.dump(request.args)
        l = items
        if 'id' in params:
            l = [i for i in l if i.id == params["id"]]
        if 'name' in params:
            l = [i for i in l if params["name"].lower() in i.name.lower()]
        if 'active' in params:
            l = [i for i in l if i.active == params["active"]]

        return l, 200
