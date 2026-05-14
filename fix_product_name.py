
import subprocess

sql_command = """
USE db_store;
ALTER TABLE product CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
UPDATE product SET name = '香脆薯片' WHERE id = 2;
SELECT * FROM product;
"""

with open('temp_fix.sql', 'w', encoding='utf-8') as f:
    f.write(sql_command)

result = subprocess.run(
    ['mysql', '-u', 'root', '-pL050325lgh@', '--default-character-set=utf8mb4', '-e', sql_command],
    capture_output=True,
    text=True,
    encoding='utf-8'
)

print("STDOUT:", result.stdout)
print("STDERR:", result.stderr)
print("Return code:", result.returncode)
