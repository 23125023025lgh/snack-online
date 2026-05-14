
import http.server
import socketserver
import webbrowser
import os

PORT = 8090
DIRECTORY = r'd:\Vmware_download\practice_class\e-commerce-platform\snack-online'

class Handler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=DIRECTORY, **kwargs)

try:
    with socketserver.TCPServer(("", PORT), Handler) as httpd:
        print(f"前端服务器已启动，访问 http://localhost:{PORT}/index.html")
        print("按 Ctrl+C 停止服务器")
        webbrowser.open(f'http://localhost:{PORT}/index.html')
        httpd.serve_forever()
except OSError as e:
    print(f"端口 {PORT} 已被占用，错误: {e}")
