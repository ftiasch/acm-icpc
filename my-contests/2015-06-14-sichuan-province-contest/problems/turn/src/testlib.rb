class Cleanroom
  def initialize
    @count   = 0
    @scripts = ['#!/usr/bin/env bash -x',
                'set -o errexit']
  end

  def to_s
    @scripts.join("\n")
  end

  def make(source)
    main, ext = source.split('.')
    case ext
    when 'cpp'
      sh "c++ $CXXFLAGS #{source} -o#{main}.exe"
    else
      raise "``#{source}'' unrecognized"
    end
  end

  def test(generator, *params)
    id = '%03d' %[@count += 1]
    inf = "../tests/#{id}"
    if generator.is_a? String
      sh "cp #{generator} #{inf}"
    else
      seed = params.shift
      seed = @count if seed == '?'
      sh "./#{generator}.exe #{seed} #{params.join(' ')} > #{inf}"
    end
    sh "./validator.exe < #{inf}"
    sh "./solution.exe < #{inf} > ../tests/#{id}.a"
  end

  def sh(command)
    @scripts << command
  end
end

def doall(&blk)
  scripts = Cleanroom.new.tap do |env|
    env.instance_eval(&blk)
  end.to_s
  puts scripts
end
