import numpy


# Probability of head/tail 
# P(Nh, Nt) = (N Nh) (1/2)^Nh (1 - 1/2)^Nt 
# Where (N Nh) is binomial coefficient, 2nd part is probability of heads and
# 3rd is probability of tails 
# This creates binomial distribution (bell curve)
#
def calc_prob_pvalue():
  m = 0
  for i in range(10000):
    trials = numpy.random.randint(2, size=30)
    if (trials.sum() >= 22):
      m += 1
  p = m / 10000.0 
  return p 

# Sampling distribution (t distribution)
# A = (v+1)/2
# B = (1 + t^2/v) 
# C = sqrt(v*pi)
# D = - (v+1/2)
# P(t;v) = r * A / (C * r * v/2) * B^D
# 
# we can use shuffling to simulate distribution fo sampling
# shuffle -> rearrange -> compute means -> plot
# repeat again
# shuffling works if null hypothesis assumes two groups are equivalent
#
# bootstrap resampling - drawing samples with replacements
def bootstrap_resampling(N):
  xbar = []
  for i in range(10000):
    sample = N[numpy.random.randint(20, size=20)]
    xbar[i] = mean(sample)
  print(means(xbar))
  print(std(xbar))

def bootstrap_linear_regression(N):
  result = []
  for i in range(10000):
    i = numpy.random.randint(20, size=20)
    slope, intercept = fit(x[i], y[i])
    result [i] = (slope, intercept)

#statistical inference
# effect size - how big is the effect 
# confidence level - how can we quantify precision of effect (+/-)
# p-value - is difference due to chance

print(calc_prob_pvalue())
#print(bootstrap_resampling([5, 10, 39, 30, 3, 4, 51, 99]))
