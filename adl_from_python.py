import os
import glob
import zipfile
import jpype
import jpype.imports

TARGET_CLASS = "com/cabolabs/openehr/Main.class"

def jar_contains(jar_path: str, member: str) -> bool:
    try:
        with zipfile.ZipFile(jar_path, "r") as z:
            return member in z.namelist()
    except Exception:
        return False

# 1) collect candidate jars
candidates = []
candidates += glob.glob(os.path.abspath("build/libs/*.jar"))
candidates += glob.glob(os.path.abspath("*.jar"))

# 2) pick the jar that actually contains the Main class
main_jar = None
for j in candidates:
    if jar_contains(j, TARGET_CLASS):
        main_jar = j
        break

if not main_jar:
    raise SystemExit(f"Could not find {TARGET_CLASS} in any of: {candidates}")

main_jar = "adl-parser-1.0.14-SNAPSHOT-jar-with-dependencies.jar"

# 3) dependency jars (if repo has ./lib)
lib_jars = glob.glob(os.path.abspath("lib/*.jar"))

# 4) groovy runtime jars (needed to avoid groovy/lang/GroovyObject errors)
groovy_home = os.environ.get("GROOVY_HOME") or os.path.expanduser("~/.sdkman/candidates/groovy/current")
groovy_jars = glob.glob(os.path.join(groovy_home, "lib", "*.jar"))

classpath = [main_jar] + lib_jars + groovy_jars

print("Using main jar:", main_jar)
print("Groovy home:", groovy_home)
print("Classpath entries:", len(classpath))

jpype.startJVM(classpath=classpath)

from com.cabolabs.openehr import Main
Main.main(["csv", "./ckm/local/archetypes/entry/observation/"])

