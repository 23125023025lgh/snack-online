import urllib.request
import urllib.parse
import json

def test_login():
    url = "http://localhost:8080/api/user/login"
    headers = {"Content-Type": "application/json"}
    data = json.dumps({"phone": "13800138000"}).encode("utf-8")
    
    try:
        req = urllib.request.Request(url, data=data, headers=headers, method="POST")
        with urllib.request.urlopen(req) as response:
            status_code = response.getcode()
            response_body = response.read().decode("utf-8")
            print(f"Login - Status Code: {status_code}")
            print(f"Login - Response: {response_body}")
            return status_code, json.loads(response_body)
    except urllib.error.HTTPError as e:
        print(f"HTTP Error: {e.code} - {e.read().decode('utf-8')}")
        return e.code, None
    except Exception as e:
        print(f"Error: {e}")
        return None, None

def test_products_without_auth():
    url = "http://localhost:8080/api/product/list?shopId=1"
    
    try:
        req = urllib.request.Request(url, method="GET")
        with urllib.request.urlopen(req) as response:
            status_code = response.getcode()
            response_body = response.read().decode("utf-8")
            print(f"Products (no auth) - Status Code: {status_code}")
            print(f"Products (no auth) - Response: {response_body}")
            return status_code, response_body
    except urllib.error.HTTPError as e:
        print(f"HTTP Error: {e.code} - {e.read().decode('utf-8')}")
        return e.code, e.read().decode('utf-8')
    except Exception as e:
        print(f"Error: {e}")
        return None, str(e)

def test_products_with_auth(token):
    url = "http://localhost:8080/api/product/list?shopId=1"
    headers = {"Authorization": f"Bearer {token}"}
    
    try:
        req = urllib.request.Request(url, headers=headers, method="GET")
        with urllib.request.urlopen(req) as response:
            status_code = response.getcode()
            response_body = response.read().decode("utf-8")
            print(f"Products (with auth) - Status Code: {status_code}")
            print(f"Products (with auth) - Response: {response_body}")
            return status_code, response_body
    except urllib.error.HTTPError as e:
        print(f"HTTP Error: {e.code} - {e.read().decode('utf-8')}")
        return e.code, e.read().decode('utf-8')
    except Exception as e:
        print(f"Error: {e}")
        return None, str(e)

if __name__ == "__main__":
    print("=== Testing Login ===")
    login_status, login_data = test_login()
    
    if login_status == 200 and login_data:
        token = login_data.get("token")
        print(f"\nToken obtained: {token[:20]}...\n")
        
        print("=== Testing Products without Auth ===")
        test_products_without_auth()
        
        print("\n=== Testing Products with Auth ===")
        test_products_with_auth(token)
