#!/usr/bin/env bash -x
set -o errexit
echo > tmp.md
(cd ../problems/easy/src && ./doall.sh)
cat ../problems/easy/tests/???   > ../problems/easy/input
cat ../problems/easy/tests/???.a > ../problems/easy/output
echo > p.md
cat ../problems/easy/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/easy/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/easy/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/easy/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/easy/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/carry/src && ./doall.sh)
cat ../problems/carry/tests/???   > ../problems/carry/input
cat ../problems/carry/tests/???.a > ../problems/carry/output
echo > p.md
cat ../problems/carry/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/carry/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/carry/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/carry/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/carry/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/censor/src && ./doall.sh)
cat ../problems/censor/tests/???   > ../problems/censor/input
cat ../problems/censor/tests/???.a > ../problems/censor/output
echo > p.md
cat ../problems/censor/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/censor/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/censor/tests/002 | sed 's/^/    /' >> p.md
cat ../problems/censor/tests/003 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/censor/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/censor/tests/002.a | sed 's/^/    /' >> p.md
cat ../problems/censor/tests/003.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/cover/src && ./doall.sh)
cat ../problems/cover/tests/???   > ../problems/cover/input
cat ../problems/cover/tests/???.a > ../problems/cover/output
echo > p.md
cat ../problems/cover/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/cover/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/cover/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/cover/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/cover/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/rectangle/src && ./doall.sh)
cat ../problems/rectangle/tests/???   > ../problems/rectangle/input
cat ../problems/rectangle/tests/???.a > ../problems/rectangle/output
echo > p.md
cat ../problems/rectangle/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/rectangle/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/rectangle/tests/002 | sed 's/^/    /' >> p.md
cat ../problems/rectangle/tests/003 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/rectangle/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/rectangle/tests/002.a | sed 's/^/    /' >> p.md
cat ../problems/rectangle/tests/003.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/necklace/src && ./doall.sh)
cat ../problems/necklace/tests/???   > ../problems/necklace/input
cat ../problems/necklace/tests/???.a > ../problems/necklace/output
echo > p.md
cat ../problems/necklace/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/necklace/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/necklace/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/necklace/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/necklace/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/party/src && ./doall.sh)
cat ../problems/party/tests/???   > ../problems/party/input
cat ../problems/party/tests/???.a > ../problems/party/output
echo > p.md
cat ../problems/party/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/party/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/party/tests/002 | sed 's/^/    /' >> p.md
cat ../problems/party/tests/003 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/party/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/party/tests/002.a | sed 's/^/    /' >> p.md
cat ../problems/party/tests/003.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/rmq/src && ./doall.sh)
cat ../problems/rmq/tests/???   > ../problems/rmq/input
cat ../problems/rmq/tests/???.a > ../problems/rmq/output
echo > p.md
cat ../problems/rmq/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/rmq/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/rmq/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/rmq/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/rmq/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/travel/src && ./doall.sh)
cat ../problems/travel/tests/???   > ../problems/travel/input
cat ../problems/travel/tests/???.a > ../problems/travel/output
echo > p.md
cat ../problems/travel/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/travel/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/travel/tests/002 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/travel/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/travel/tests/002.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
(cd ../problems/turn/src && ./doall.sh)
cat ../problems/turn/tests/???   > ../problems/turn/input
cat ../problems/turn/tests/???.a > ../problems/turn/output
echo > p.md
cat ../problems/turn/src/problem.md >> p.md
echo >> p.md
echo \#\# Sample Input >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/turn/tests/001 | sed 's/^/    /' >> p.md
cat ../problems/turn/tests/002 | sed 's/^/    /' >> p.md
cat ../problems/turn/tests/003 | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
echo \#\# Sample Output >> p.md
echo >> p.md
echo \`\`\`>> p.md
cat ../problems/turn/tests/001.a | sed 's/^/    /' >> p.md
cat ../problems/turn/tests/002.a | sed 's/^/    /' >> p.md
cat ../problems/turn/tests/003.a | sed 's/^/    /' >> p.md
echo \`\`\`>> p.md
echo >> p.md
cat p.md >> tmp.md
rm #{p}.md
pandoc tmp.md --template=template.tex -t latex > tmp.tex
xelatex tmp.tex
mv tmp.pdf problems.pdf
rm -rf tmp.*
