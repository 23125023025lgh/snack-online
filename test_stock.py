
import urllib.request
import json

print('=== 测试库存扣减功能 ===')
print()

# 1. 查看下单前的库存
print('1. 下单前的商品库存:')
req = urllib.request.Request('http://localhost:8080/api/product/list?shopId=1')
with urllib.request.urlopen(req) as response:
    products = json.loads(response.read().decode('utf-8'))
    for p in products:
        print(f"   商品ID: {p['id']}, 名称: {p['name']}, 库存: {p['stock']}")

print()

# 2. 登录获取token
login_data = json.dumps({'phone': '13800138000'}).encode('utf-8')
login_req = urllib.request.Request('http://localhost:8080/api/user/login', data=login_data, headers={'Content-Type': 'application/json'})
with urllib.request.urlopen(login_req) as response:
    login_result = json.loads(response.read().decode('utf-8'))
    token = login_result['token']
    print('2. 登录成功，获取到Token')

print()

# 3. 创建订单（会扣减库存）
order_data = json.dumps({'userId': 1, 'productId': 1, 'quantity': 1, 'shopId': 1}).encode('utf-8')
order_req = urllib.request.Request('http://localhost:8080/api/order/create', data=order_data, headers={'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token})
with urllib.request.urlopen(order_req) as response:
    order_result = json.loads(response.read().decode('utf-8'))
    print(f"3. 下单成功，订单ID: {order_result['id']}")

print()

# 4. 查看下单后的库存
print('4. 下单后的商品库存:')
req = urllib.request.Request('http://localhost:8080/api/product/list?shopId=1')
with urllib.request.urlopen(req) as response:
    products = json.loads(response.read().decode('utf-8'))
    for p in products:
        print(f"   商品ID: {p['id']}, 名称: {p['name']}, 库存: {p['stock']}")

print()
print('=== 测试完成 ===')
