--使用luajava创建java类的实例（对象）
-- local logger = luajava.newInstance("Logger")
--调用对象方法
-- logger:TestLogger("Test call java in lua0")

--使用luajava绑定一个java类
-- local logger_method = luajava.bindClass("Logger");
--调用类的静态方法/变量
-- logger_method:info("test call static java function in lua")
-- print(logger_method.TAG)
-- 使用绑定类创建类的实例（对象）
-- local logger_instance = luajava.new(logger_method)
-- 调用对象方法
-- logger_instance:TestLogger("Test call java in lua1")

--无参函数
function hello()
    print 'hello2---rodking---------------------'
end

function hello()
    --print 'hello466'
    print 'hello466---------------------222rodking ddd '
end

function hi(some)
    print ('every day is a new day:'..some)
end

function testObject(obj)

    print ('-----------------------------------------------------------------')
    print ('lua testObj:a  '..obj:getA())
    print ('lua testObj:b  '..obj:getB())
    print ('lua testObj:sum '..obj:add())
    obj:setA(100)
    print ('-----------------------------------------------------------------')
    return obj
end

function testLua2Java()
    local clazz = luajava.bindClass("Main")
    print ('-----------------------------------------------------------------')
    clazz.obj = luajava.newInstance("TestObject",1000,1000)
    print ('lua testObj(1):a  '..clazz.obj:getA())
    print ('lua testObj(1):b  '..clazz.obj:getB())
    print ('lua testObj(1):sum '..clazz.obj:add())
    print ('-----------------------------------------------------------------')
end