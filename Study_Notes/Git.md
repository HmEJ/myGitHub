## git 指令
- 常用指令

    `git init`初始化本地仓库

    `git add 文件名` 将文件存入暂存区

    `git commit -m "message"` 将文件提交到本地仓库

    `git branch` 查看分支

    `git checkout 分支名` 切换分支

    `git remote add orgin ssh密钥` 连接远端仓库

    `git push 文件名` 将本地仓库的文件push到远端

    `git pull` 将远端仓库文件pull到本地仓库

    `git push --set-upstream origin 分支名` push时关联远端分支

    `git pull --set-upstream origin 分支名` pull时关联远端分支

- 出现`refusing to merge unrelated histories`报错时，说明本地仓库和远端仓库不一致，解决方法：`git pull/push --allow-unrelated-histories`

## 修改历史 git message信息
1. `git log`查看`commit`日志
2. 使用回滚命令编辑git message信息： `git rebase -i [提交记录的识别码]`
3. 修改git message前的关键字，修改message一般改为reword
	- **pick**：保留该 commit
	- **reword**：保留该 commit，但我需要修改该commit的 Message
	- **edit**：保留该 commit, 但我要停下来修改该提交(包括修改文件)
	- **squash**：将该 commit 和前一个 commit 合并
	- **fixup**：将该 commit 和前一个 commit 合并，但我不要保留该提交的注释信息
	- **exec**：执行 shell 命令
	- **drop**：丢弃这个 commit
4. 修改完成 强制push到远端 : `git push origin [分支] -f`

## git message规范
**commit message格式**
```text
<type>(<scope>): <subject>
```
**type(必须)**

用于说明git commit的类别，只允许使用下面的标识。

feat：新功能（feature）。

fix/to：修复bug，可以是QA发现的BUG，也可以是研发自己发现的BUG。

- fix：产生diff并自动修复此问题。适合于一次提交直接修复问题
- to：只产生diff不自动修复此问题。适合于多次提交。最终修复问题提交时使用fix

docs：文档（documentation）。

style：格式（不影响代码运行的变动）。

refactor：重构（即不是新增功能，也不是修改bug的代码变动）。

perf：优化相关，比如提升性能、体验。

test：增加测试。

chore：构建过程或辅助工具的变动。

revert：回滚到上一个版本。

merge：代码合并。

sync：同步主线或分支的Bug。

**scope(可选)**

scope用于说明 commit 影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。

例如在Angular，可以是location，browser，compile，compile，rootScope， ngHref，ngClick，ngView等。如果你的修改影响了不止一个scope，你可以使用*代替。

**subject(必须)**

subject是commit目的的简短描述，不超过50个字符。

建议使用中文（感觉中国人用中文描述问题能更清楚一些）。
- 结尾不加句号或其他标点符号。
- 根据以上规范git commit message将是如下的格式：
```text
fix(DAO):用户查询缺少username属性 
feat(Controller):用户查询接口开发
```