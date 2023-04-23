from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticated
from main.models import Product
from main.serializers import ProductSerializer

class ProductViewSet(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated,)

    queryset = Product.objects.all().order_by('name')
    serializer_class = ProductSerializer
