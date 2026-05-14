import urllib.request
import urllib.parse
import json

# 测试直接访问 store-service 的商品接口（不带认证）
def test_store_products():
    url = "http://localhost:8081/api/product/list?shopId=1"
    
    try:
        req = urllib.request.Request(url, method="GET")
        with urllib.request.urlopen(req) as response:
            status_code = response.getcode()
            response_body = response.read().decode("utf-8")
            print(f"直接访问 store-service - Status Code: {status_code}")
            print(f"Response: {response_body}")
            return status_code, json.loads(response_body)
    except urllib.error.HTTPError as e:
        print(f"HTTP Error: {e.code} - {e.read().decode('utf-8')}")
        return e.code, None
    except Exception as e:
        print(f"Error: {e}")
        return None, None

# 测试通过 gateway 访问商品接口（不带认证）
def test_gateway_products():
    url = "http://localhost:8080/api/product/list?shopId=1"
    
    try:
        req = urllib.request.Request(url, method="GET")
        with urllib.request.urlopen(req) as response:
            status_code = response.getcode()
            response_body = response.read().decode("utf-8")
            print(f"\n通过 gateway 访问 - Status Code: {status_code}")
            print(f"Response: {response_body}")
            return status_code, json.loads(response_body)
    except urllib.error.HTTPError as e:
        print(f"\nHTTP Error: {e.code} - {e.read().decode('utf-8')}")
        return e.code, None
    except Exception as e:
        print(f"\nError: {e}")
        return None, None

if __name__ == "__main__":
    print("=== 测试商品列表接口 ===")
    
    # 测试直接访问
    print("\n--- 直接访问 store-service (8081端口) ---")
    test_store_products()
    
    # 测试通过 gateway
    print("\n--- 通过 gateway (8080端口) ---")
    test_gateway_products()
