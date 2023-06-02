from flask import Flask, render_template, request
import time
import re

app = Flask(__name__)

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/upload', methods=['POST'])
def upload():
    import time
    inicio = time.time()
    archivo = request.files['archivo']
    archivo.save(archivo.filename)
    archivo = open(archivo.filename, 'r')
    contenido = archivo.read()
    archivo.close()
    contenido = re.sub(r"\s+", " ", contenido)
    texto = contenido.split()
    nums = get_nums(texto)
    lista = bubble_sort(nums)
    fin = time.time()
    tiempo = fin - inicio
    data = {
        "lista": lista,
        "tiempo": tiempo
    }
    return render_template("update.html", data = data)

def bubble_sort(lista):
    n = len(lista)
    for i in range(n - 1):
        for j in range(n - 1 - i):
            if lista[j] > lista[j + 1]:
                lista[j], lista[j + 1] = lista[j + 1], lista[j]
    return lista


def get_nums(texto: list):
    nums = []
    for num in texto:
        nums.append(int(num))
    return nums


if __name__ == "__main__":
    app.run(debug = True, port = 5500)