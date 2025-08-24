class EcuacionLineal:
    def __init__(self, a, b, c, d, e, f):
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.e = e
        self.f = f
        self.x = None
        self.y = None

    def X(self):
        self.x = ((self.e*self.d)-(self.b*self.f))/((self.a*self.d)-(self.b*self.c))

    def Y(self):
        self.y = ((self.a*self.f)-(self.e*self.c))/((self.a*self.d)-(self.b*self.c))

    def tieneSolucion(self):
        if((self.a*self.d)-(self.b*self.c) != 0):
            self.X()
            self.Y()
            print(self.x, ", ",self.y)
        else:
            print("la escuacion no tiene solucion")
try:
    v = input("Ingrese a, b, c, d, e, f: ").split()
    a, b, c, d, e, f = map(float, v)

    ec1 = EcuacionLineal(a, b, c, d, e, f)
    ec1.tieneSolucion()
except ValueError:
    print("Error")