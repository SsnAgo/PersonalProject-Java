# 代码规范

---

- **缩进**
> 存在嵌套关系的缩进使用四个空格缩进
 ```
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public double getMoney() {
        if(money > 0)
            return money;
        else
            System.out.println("钱不够");
    }
 ```

- **变量名**
> 使用lowerCamelCase风格，并遵从驼峰形式。
  例如：lastName、minMoney

- **每行最多字符数**
> 每行最多字符数为100

- **函数最大行数**
> 简易函数不超过15行（无复杂逻辑关系），复杂函数不超过50行（有复杂逻辑关系）。

- **函数、类命名**
> 函数名使用lowerCamelCase风格，类名使用UpperCamelCase风格，都遵从驼峰形式。
  抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。
  例如：（函数名）getLastName()、（类名）UserManager、（抽象类）AbstractCar、（异常类）NumException、（测试类）UserMapperTest

- **常量**
> 所有字母大写，单词间用下划线隔开，力求语义表达完整清楚
  例如：MIN_LINK_COUNT、MAX_LINK_COUNT

- **空行规则**
> 类定义与方法定义、方法定义与方法定义、方法定义与变量声明之间空一行，类定义与变量声明、变量声明与变量声明之间可不空行。
  方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。
```
public class User {
    private int id;
    private String name;
    private String pwd;

    public User(){
    }

    public User(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    } 
```

- **注释规则**
> 方法体或是完成特定功能的函数块的注释采用//……注释在最上一行，有特殊含义的变量或者常量需要//……注释在声明的后方。
```
    //获取全部对象
    List<User> getUserList();

    //根据ID查询
    User getUserById(int id);

    //模糊查询
    List<User> getUserListLike(String str);
    
    String lastName;//最后一个对象的名字
    
    try{
            //执行Sql
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

            for(User user : userList){
                System.out.println(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //记得关闭SqlSession
            sqlSession.close();
        }
```

- **操作符前后空格**
> 操作符前后各加一个空格
```
    int a = 8;
    int b = 9;
    int c = a + b;
```

- **其他规则**
  + 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
  例如：com.ning.dao、com.ning.pojo
  + 所有的覆写方法，必须加@Override注解
```
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
```