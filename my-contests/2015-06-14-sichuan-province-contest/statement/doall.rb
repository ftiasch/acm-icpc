def sh(cmd)
  puts cmd
end

sample_counts = [2, 2, 3, 2, 3, 2, 3, 2, 2, 3]

puts '#!/usr/bin/env bash -x'
puts 'set -o errexit'
sh 'echo > tmp.md'
%w(easy carry censor cover rectangle necklace party rmq travel turn).each_with_index do |p, i|
  pd = "../problems/#{p}"

  sh "(cd #{pd}/src && ./doall.sh)"
  sh "cat #{pd}/tests/???   > #{pd}/input"
  sh "cat #{pd}/tests/???.a > #{pd}/output"

  sh 'echo > p.md'
  sh "cat #{pd}/src/problem.md >> p.md"
  sh 'echo >> p.md'
  sh 'echo \#\# Sample Input >> p.md'
  sh 'echo >> p.md'
  sh 'echo \`\`\`>> p.md'
  (1..sample_counts[i]).each do |j|
    sh "cat #{pd}/tests/%03d | sed 's/^/    /' >> p.md" %[j]
  end
  sh 'echo \`\`\`>> p.md'
  sh 'echo >> p.md'
  sh 'echo \#\# Sample Output >> p.md'
  sh 'echo >> p.md'
  sh 'echo \`\`\`>> p.md'
  (1..sample_counts[i]).each do |j|
    sh "cat #{pd}/tests/%03d.a | sed 's/^/    /' >> p.md" %[j]
  end
  sh 'echo \`\`\`>> p.md'
  sh 'echo >> p.md'
  sh 'cat p.md >> tmp.md'
  sh 'rm #{p}.md'
end

sh 'pandoc tmp.md --template=template.tex -t latex > tmp.tex'
sh 'xelatex tmp.tex'
sh 'mv tmp.pdf problems.pdf'
sh 'rm -rf tmp.*'
