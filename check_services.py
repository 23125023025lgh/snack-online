
import socket
import time

def check_port(host, port, service_name):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(2)
        result = sock.connect_ex((host, port))
        sock.close()
        if result == 0:
            print("[OK] %s:%d - running" % (service_name, port))
            return True
        else:
            print("[FAIL] %s:%d - not running" % (service_name, port))
            return False
    except Exception as e:
        print("[FAIL] %s:%d - not running (%s)" % (service_name, port, str(e)))
        return False

print("=" * 50)
print("Check All Services Status...")
print("=" * 50)

# 检查前端服务
check_port("localhost", 8090, "Frontend")
check_port("localhost", 8080, "Gateway")
check_port("localhost", 8081, "Store Service")
check_port("localhost", 8082, "Trade Service")
check_port("localhost", 8083, "Pay Service")
check_port("localhost", 8899, "Nacos")

print("=" * 50)
